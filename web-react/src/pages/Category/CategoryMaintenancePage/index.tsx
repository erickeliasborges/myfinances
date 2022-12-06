import {
  FormControl,
  FormErrorMessage,
  FormLabel,
  Input,
} from "@chakra-ui/react";
import { ChangeEvent, useEffect, useState } from "react";
import { useForm } from "react-hook-form";
import { useNavigate, useParams } from "react-router-dom";
import { ICategory } from "../../../commons/interfaces";
import { CrudMaintenance } from "../../../components/Crud/CrudMaintenance";
import CategoryService from "../../../services/CategoryService";

export function CategoryMaintenancePage() {
  const {
    handleSubmit,
    register,
    formState: { errors, isSubmitting },
    setError,
    setFocus,
    reset,
    setValue,
  } = useForm<ICategory>();

  const [pendingApiCall, setPendingApiCall] = useState(false);
  const [apiError, setApiError] = useState(false);
  const navigate = useNavigate();
  const { id } = useParams();

  useEffect(() => {
    setFocus("name");
    if (id) {
      CategoryService.findById(parseInt(id))
        .then((response) => {
          if (response.data) {
            setValue("id", response.data.id);
            setValue("name", response.data.name);
          }
        })
        .catch((responseError) => {
          setApiError(true);
        });
    }
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

  const onSubmit = (category: ICategory) => {
    setPendingApiCall(true);
    CategoryService.save(category)
      .then((response) => {
        setPendingApiCall(false);
        navigate("/categories");
      })
      .catch((responseError) => {
        setPendingApiCall(false);
        if (responseError.response.data.validationErrors) {
          setErrors(responseError.response.data.validationErrors);
          return;
        }
        setApiError(true);
      });
  };

  return (
    <CrudMaintenance
      title="Categoria"
      onSubmitForm={handleSubmit(onSubmit)}
      linkCancelMaintenance="/categories"
    >
      <FormControl variant="floating" id="name" isInvalid={errors.name && true}>
        <Input
          placeholder=" "
          type="text"
          {...register("name", {
            required: "O campo nome é obrigatório",
            onChange: onChange,
          })}
        />
        <FormLabel>Nome</FormLabel>
        <FormErrorMessage>
          {errors.name && errors.name.message}
        </FormErrorMessage>
      </FormControl>

      {apiError && (
        <div className="alert alert-danger">
          Falha ao cadastrar a categoria.
        </div>
      )}
    </CrudMaintenance>
  );
}
