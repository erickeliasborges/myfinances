import {
  FormControl,
  FormErrorMessage,
  FormLabel,
  Input,
  Select,
  SimpleGrid,
} from "@chakra-ui/react";
import { ChangeEvent, useEffect, useState } from "react";
import { useForm } from "react-hook-form";
import { useNavigate, useParams } from "react-router-dom";
import {
  getEnumByKey,
  getEnumByValue,
  TypeMovementEnum,
} from "../../../commons/enums";
import { IMovement, IAccount, ICategory } from "../../../commons/interfaces";
import { CrudMaintenance } from "../../../components/Crud/CrudMaintenance";
import MovementService from "../../../services/MovementService";
import AccountService from "../../../services/AccountService";
import CategoryService from "../../../services/CategoryService";
import AuthService from "../../../services/AuthService";

export function MovementMaintenancePage() {
  const {
    handleSubmit,
    register,
    formState: { errors, isSubmitting },
    setError,
    setFocus,
    reset,
    setValue,
    getValues,
    clearErrors
  } = useForm<IMovement>();

  const [pendingApiCall, setPendingApiCall] = useState(false);
  const [apiError, setApiError] = useState("");
  const navigate = useNavigate();
  const { id } = useParams();
  const [accounts, setAccounts] = useState([]);
  const [categories, setCategories] = useState([]);
  const [destinationAccountVisible, setDestinationAccountVisible] =
    useState(false);

  const setCurrentDate = () => {
    // let date = new Date();
    // // Achar uma forma melhor de setar a data atual
    // setValue("dueDate", new Date(date.getTime() - (date.getTimezoneOffset() * 60000)).toJSON().substring(0, 19) as unknown as Date)
  }

  useEffect(() => {
    setFocus("description");

    setCurrentDate();

    AuthService.loggedUser()
      .then((user: any) => {
        setValue("account.user", user.data);

        AccountService.findByUserId(user.data.id)
          .then((response) => {
            setAccounts(response.data);
            if (!id && response.data[0]) setValue("account", response.data[0]);
            CategoryService.findAll()
              .then((response) => {
                setCategories(response.data);
                if (id) {
                  MovementService.findById(parseInt(id))
                    .then((response) => {
                      if (response.data) {
                        setValue("id", response.data.id);
                        setValue("description", response.data.description);
                        setValue("account", response.data.account);
                        setValue(
                          "destinationAccount",
                          response.data.destinationAccount
                        );
                        setValue("value", response.data.value);
                        setValue("dueDate", response.data.dueDate);
                        setValue("category", response.data.category);
                        setValue("typeMovement", response.data.typeMovement);
                        configFields();
                      }
                    })
                    .catch((responseError) => {
                      setApiError("Falha ao buscar conta");
                    });
                } else if (response.data[0])
                  setValue("category", response.data[0]);
              })
              .catch((error) => {
                setApiError("Falha ao carregar a lista de categorias.");
              });
          })
          .catch((error) => {
            setApiError("Falha ao carregar a lista de contas.");
          });
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  const configFields = () => {
    setDestinationAccountVisible(
      getValues("typeMovement") ==
        getEnumByValue(TypeMovementEnum, TypeMovementEnum.TRANSFERENCIA_CONTAS)
    );
  };

  const setErrors = (errors: any) => {
    Object.keys(errors).forEach((value) => {
      setError(value as any, { message: errors[value] });
    });
  };

  const onChange = (event: ChangeEvent<HTMLInputElement>) => {
    const { value, name } = event.target;

    setValue(name as any, value);

    setErrors((previousErrors: any) => {
      return {
        ...previousErrors,
        [name]: "",
      };
    });
  };

  const validAccountTransfer = () => {
    if (getValues("account.id") == getValues("destinationAccount.id")) {
      setError("destinationAccount", {
        message: "A conta destino deve ser diferente da conta de origem",
      });
      return false;
    }

    return true;
  };

  const onSubmit = (movement: IMovement) => {
    if (!validAccountTransfer()) return;

    setApiError("");
    setPendingApiCall(true);    

    MovementService.save(movement)
      .then((response) => {
        setPendingApiCall(false);
        navigate("/movements");
      })
      .catch((responseError) => {
        setPendingApiCall(false);
        if (responseError.response.data.validationErrors) {
          setErrors(responseError.response.data.validationErrors);
          return;
        }
        console.log(responseError);
        setApiError("Falha ao salvar movimentação.");
      });
  };

  return (
    <CrudMaintenance
      title="Movimentação"
      onSubmitForm={handleSubmit(onSubmit)}
      linkCancelMaintenance="/movements"
    >
      <FormControl
        variant="floating"
        id="description"
        isInvalid={errors.description && true}
      >
        <Input
          placeholder=" "
          type="text"
          {...register("description", {
            required: "O campo descrição é obrigatório",
            onChange: onChange,
          })}
        />
        <FormLabel>Descrição</FormLabel>
        <FormErrorMessage>
          {errors.description && errors.description.message}
        </FormErrorMessage>
      </FormControl>

      <FormControl isInvalid={errors.typeMovement && true} variant="floating">
        <Select disabled={id != null}
          id="typeMovement"
          {...register("typeMovement", {
            required: "O campo tipo é obrigatório",
            onChange(event) {
              onChange(event);
              configFields();
            },
          })}
          size="sm"
        >
          {Object.keys(TypeMovementEnum).map((value) => {
            return (
              <option key={value} value={value}>
                {getEnumByKey(TypeMovementEnum, value)}
              </option>
            );
          })}
        </Select>

        <FormLabel>Tipo</FormLabel>
        <FormErrorMessage>
          {errors.typeMovement && errors.typeMovement.message}
        </FormErrorMessage>
      </FormControl>

      <SimpleGrid columns={2} spacing={2}>
        <FormControl
          variant="floating"
          id="value"
          isInvalid={errors.value && true}
        >
          <Input
            placeholder=" "
            type="number"
            {...register("value", {
              required: "O campo valor é obrigatório",
              onChange: onChange,
            })}
          />
          <FormLabel>Valor</FormLabel>
          <FormErrorMessage>
            {errors.value && errors.value.message}
          </FormErrorMessage>
        </FormControl>
        <FormControl
          variant="floating"
          id="dueDate"
          isInvalid={errors.dueDate && true}
        >
          <Input
            placeholder=" "
            type="datetime-local"
            {...register("dueDate", {
              onChange: onChange,
            })}
          />
          <FormLabel>Data de vencimento</FormLabel>
          <FormErrorMessage>
            {errors.dueDate && errors.dueDate.message}
          </FormErrorMessage>
        </FormControl>
      </SimpleGrid>

      <FormControl isInvalid={errors.account && true} variant="floating">
        <Select
          {...register("account.id", {
            required: "O campo conta é obrigatório",
            onChange: onChange,
          })}
          size="sm"
        >
          {accounts.map((account: IAccount) => (
            <option key={account.id} value={account.id}>
              {account.agency} - {account.number} - {account.bank.name}
            </option>
          ))}
        </Select>

        <FormLabel>Conta</FormLabel>
        <FormErrorMessage>
          {errors.account && errors.account.message}
        </FormErrorMessage>
      </FormControl>

      {destinationAccountVisible && (
        <FormControl
          isInvalid={errors.destinationAccount && true}
          variant="floating"
        >
          <Select
            {...register("destinationAccount.id", {
              onChange(event) {
                clearErrors("destinationAccount");
                onChange(event);                   
              },
              required: "O campo conta destino é obrigatório",
            })}
            size="sm"
          >
            {accounts.map((account: IAccount) => (
              <option key={account.id} value={account.id}>
                {account.agency} - {account.number} - {account.bank.name}
              </option>
            ))}
          </Select>

          <FormLabel>Conta destino</FormLabel>
          <FormErrorMessage>
            {errors.destinationAccount && errors.destinationAccount!.message}
          </FormErrorMessage>
        </FormControl>
      )}

      <FormControl isInvalid={errors.category && true} variant="floating">
        <Select
          {...register("category.id", {
            required: "O campo categoria é obrigatório",
            onChange: onChange,
          })}
          size="sm"
        >
          {categories.map((category: ICategory) => (
            <option key={category.id} value={category.id}>
              {category.name}
            </option>
          ))}
        </Select>

        <FormLabel>Categoria</FormLabel>
        <FormErrorMessage>
          {errors.category && errors.category.message}
        </FormErrorMessage>
      </FormControl>

      {apiError && <div className="alert alert-danger">{apiError}</div>}
    </CrudMaintenance>
  );
}
