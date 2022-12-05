import {
    FormControl,
    FormErrorMessage,
    FormLabel,
    Input,
  } from "@chakra-ui/react";
  import { ChangeEvent, useEffect, useState } from "react";
  import { useForm } from "react-hook-form";
  import { useNavigate, useParams } from "react-router-dom";
  import { IBank } from "../../../commons/interfaces";
  import { CrudMaintenance } from "../../../components/Crud/CrudMaintenance";
  import BankService from "../../../service/BankService";
  
  export function BankMaintenancePage() {
    const [form, setForm] = useState({
      id: undefined,
      name: "",
    });
  
    const {
      handleSubmit,
      register,
      formState: { errors, isSubmitting },
      setError,
      setFocus,
      reset,
    } = useForm<IBank>();
  
    const [pendingApiCall, setPendingApiCall] = useState(false);
    const [apiError, setApiError] = useState(false);
    const navigate = useNavigate();
    const { id } = useParams();
  
    useEffect(() => {
      setFocus("name");
      if (id) {
        form.name;
        BankService.findById(parseInt(id))
          .then((response) => {
            if (response.data) {
              setForm({
                id: response.data.id,
                name: response.data.name,
              });
            }
          })
          .catch((responseError) => {
            setApiError(true);
          });
      }
    }, []);
  
    const setErrors = (errors: any) => {
      Object.keys(errors).forEach((value) => {
        setError(value as "name", { message: errors[value] });
      });
    };
  
    const onChange = (event: ChangeEvent<HTMLInputElement>) => {
      const { value, name } = event.target;
      setForm((previousForm) => {
        return {
          ...previousForm,
          [name]: value,
        };
      });
  
      setErrors((previousErrors: any) => {
        return {
          ...previousErrors,
          [name]: "",
        };
      });
    };
  
    const onSubmit = () => {
      const category: IBank = {
        id: parseInt(id!),
        name: form.name,
      };
      setPendingApiCall(true);
      BankService.save(category)
        .then((response) => {
          setPendingApiCall(false);
          navigate("/banks");
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
        title="Banco"
        onSubmitForm={handleSubmit(onSubmit)}
        linkCancelMaintenance="/banks"
      >
        <FormControl variant="floating" id="name" isInvalid={errors.name && true}>
          <Input
            placeholder=" "
            type="text"
            value={form.name}
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
            Falha ao cadastrar o banco.
          </div>
        )}
      </CrudMaintenance>
    );
  }
  