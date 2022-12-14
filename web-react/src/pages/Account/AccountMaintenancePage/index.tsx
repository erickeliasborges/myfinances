import {
  FormControl,
  FormErrorMessage,
  FormLabel,
  Input,
  Select,
} from "@chakra-ui/react";
import { ChangeEvent, useEffect, useState } from "react";
import { useForm } from "react-hook-form";
import { useNavigate, useParams } from "react-router-dom";
import { TypeAccountEnum } from "../../../commons/enums";
import { IAccount, IBank } from "../../../commons/interfaces";
import { CrudMaintenance } from "../../../components/Crud/CrudMaintenance";
import AccountService from "../../../services/AccountService";
import AuthService from "../../../services/AuthService";
import BankService from "../../../services/BankService";

export function AccountMaintenancePage() {
  const {
    handleSubmit,
    register,
    formState: { errors, isSubmitting },
    setError,
    setFocus,
    reset,
    setValue,
  } = useForm<IAccount>();

  const [pendingApiCall, setPendingApiCall] = useState(false);
  const [apiError, setApiError] = useState("");
  const navigate = useNavigate();
  const { id } = useParams();
  const [banks, setBanks] = useState([]);

  useEffect(() => {
    setFocus("description");

    setLoggedUser();

    BankService.findAll()
      .then((response) => {
        setBanks(response.data);
        if (id) {
          AccountService.findById(parseInt(id))
            .then((response) => {
              if (response.data) {
                setValue("id", response.data.id);
                setValue("user", response.data.user);
                setValue("description", response.data.description);
                setValue("agency", response.data.agency);
                setValue("number", response.data.number);
                setValue("bank", response.data.bank);                
                setValue("typeAccount", response.data.typeAccount);
              }
            })
            .catch((responseError) => {
              setApiError("Falha ao buscar conta");
            });
        } else if (response.data[0]) setValue("bank", response.data[0]);
      })
      .catch((error) => {
        setApiError("Falha ao carregar a lista de bancos.");
      });
  }, []);

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

  const setLoggedUser = () => {
    AuthService.loggedUser()
      .then((user: any) => {
        setValue("user", user.data);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const onSubmit = (account: IAccount) => {
    setApiError("");
    setPendingApiCall(true);

    AccountService.duplicatedAccount(account)
      .then((response) => {
        if (response.data.duplicated) {
          setPendingApiCall(false);
          setApiError(response.data.message);
          return;
        }
        AccountService.save(account)
          .then((response) => {
            setPendingApiCall(false);
            navigate("/accounts");
          })
          .catch((responseError) => {
            setPendingApiCall(false);
            if (responseError.response.data.validationErrors) {
              setErrors(responseError.response.data.validationErrors);
              return;
            }
            console.log(responseError);
            setApiError("Falha ao salvar conta.");
          });
      })
      .catch((error) => {
        console.log(error);
        setApiError("Falha ao verificar se a conta está duplicada.");
      });
  };

  return (
    <CrudMaintenance
      title="Conta"
      onSubmitForm={handleSubmit(onSubmit)}
      linkCancelMaintenance="/accounts"
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

      <FormControl
        variant="floating"
        id="agency"
        isInvalid={errors.agency && true}
      >
        <Input
          placeholder=" "
          type="text"
          {...register("agency", {
            required: "O campo agência é obrigatório",
            onChange: onChange,
          })}
        />
        <FormLabel>Agência</FormLabel>
        <FormErrorMessage>
          {errors.agency && errors.agency.message}
        </FormErrorMessage>
      </FormControl>

      <FormControl
        variant="floating"
        id="number"
        isInvalid={errors.number && true}
      >
        <Input
          placeholder=" "
          type="number"
          {...register("number", {
            required: "O campo número é obrigatório",
            onChange: onChange,
          })}
        />
        <FormLabel>Número</FormLabel>
        <FormErrorMessage>
          {errors.number && errors.number.message}
        </FormErrorMessage>
      </FormControl>

      <FormControl isInvalid={errors.bank && true} variant="floating">
        <Select
          {...register("bank.id", {
            required: "O campo banco é obrigatório",
            onChange: onChange,
          })}
          size="sm"
        >
          {banks.map((bank: IBank) => (
            <option key={bank.id} value={bank.id}>
              {bank.name}
            </option>
          ))}
        </Select>

        <FormLabel>Banco</FormLabel>
        <FormErrorMessage>
          {errors.bank && errors.bank.message}
        </FormErrorMessage>
      </FormControl>

      <FormControl isInvalid={errors.typeAccount && true} variant="floating">
        <Select
          id="typeAccount"
          {...register("typeAccount", {
            required: "O campo tipo é obrigatório",
            onChange: onChange,
          })}
          size="sm"
        >
          {Object.keys(TypeAccountEnum).map((value) => {
            return (
              <option key={value} value={value}>
                {value}
              </option>
            );
          })}
        </Select>

        <FormLabel>Tipo</FormLabel>
        <FormErrorMessage>
          {errors.typeAccount && errors.typeAccount.message}
        </FormErrorMessage>
      </FormControl>

      {apiError && <div className="alert alert-danger">{apiError}</div>}
    </CrudMaintenance>
  );
}
