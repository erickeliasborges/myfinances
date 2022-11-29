import { ChangeEvent, useState } from "react";
import { IUserSignUp } from "../../commons/interfaces";
import AuthService from "../../service/AuthService";
import { ButtonWithProgress } from "../../components/ButtonWithProgress";
import { useNavigate } from "react-router-dom";
import {
  Box,
  Button,
  Flex,
  FormControl,
  FormErrorMessage,
  FormLabel,
  Heading,
  Input,
  Link,
  Stack,
} from "@chakra-ui/react";
import { useForm } from "react-hook-form";

export function UserSignupPage() {
  const {
    handleSubmit,
    register,
    formState: { errors, isSubmitting },
    reset,
    setError,
    clearErrors,
  } = useForm<IUserSignUp>();

  const [form, setForm] = useState({
    name: "",
    username: "",
    password: "",
    email: "",
  });

  const setErrors = (errors: any) => {
    Object.keys(errors).forEach((value) => {
      setError(value as "name", { message: errors[value] });
    });
  };

  const [pendingApiCall, setPendingApiCall] = useState(false);
  const navigate = useNavigate();

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

  const onClickSignUp = () => {
    setPendingApiCall(true);
    const userSignUp: IUserSignUp = {
      name: form.name,
      username: form.username,
      password: form.password,
      email: form.email,
    };
    AuthService.signup(userSignUp)
      .then((response) => {
        setPendingApiCall(false);
        console.log(response);
        navigate("/login");
      })
      .catch((errorResponse) => {
        setPendingApiCall(false);
        console.log(errorResponse);
        if (errorResponse.response.data.validationErrors)
          setErrors(errorResponse.response.data.validationErrors);
      });
  };

  return (
    <Flex
      flexDirection="column"
      width="100wh"
      height="100vh"
      backgroundColor="gray.200"
      justifyContent="center"
      alignItems="center"
    >
      <Stack
        flexDir="column"
        mb="2"
        justifyContent="center"
        alignItems="center"
      >
        <Heading color="teal.400">Cadastre-se</Heading>
        <Box minWidth={{ base: "90%", md: "468px" }}>
          <form onSubmit={handleSubmit(onClickSignUp)}>
            <Stack
              spacing={4}
              p="1rem"
              backgroundColor="whiteAlpha.900"
              boxShadow="md"
            >
              <FormControl
                variant="floating"
                id="name"
                isInvalid={errors.name && true}
              >
                <Input
                  type="text"
                  className="form-control"
                  placeholder=" "
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
              <FormControl
                variant="floating"
                id="username"
                isInvalid={errors.username && true}
              >
                <Input
                  type="text"
                  placeholder=" "
                  value={form.username}
                  {...register("username", {
                    required: "O campo usuário é obrigatório",
                    onChange: onChange,
                  })}
                />
                <FormLabel>Usuário</FormLabel>
                <FormErrorMessage>
                  {errors.username && errors.username.message}
                </FormErrorMessage>
              </FormControl>

              <FormControl
                variant="floating"
                id="password"
                isInvalid={errors.password && true}
              >
                <Input
                  type="password"
                  placeholder=" "
                  value={form.password}
                  {...register("password", {
                    required: "O campo senha é obrigatório",
                    onChange: onChange,
                  })}
                />
                <FormLabel>Senha</FormLabel>
                <FormErrorMessage>
                  {errors.password && errors.password.message}
                </FormErrorMessage>                
              </FormControl>

              <FormControl
                variant="floating"
                id="email"
                isInvalid={errors.email && true}
              >
                <Input
                  type="email"
                  placeholder=" "
                  value={form.email}
                  {...register("email", {
                    required: "O campo email é obrigatório",
                    onChange: onChange,
                  })}
                />
                <FormLabel>E-mail</FormLabel>
                <FormErrorMessage>
                  {errors.email && errors.email.message}
                </FormErrorMessage>                
              </FormControl>

              {/* {apiError && (
                <div className="alert alert-danger text-center">{apiError}</div>
              )} */}

              <Button
                borderRadius={0}
                type="submit"
                variant="solid"
                colorScheme="teal"
                width="full"
              >
                Cadastrar
              </Button>
            </Stack>
          </form>
        </Box>
      </Stack>
      <Box>
        Já possui cadastro?{" "}
        <Link color="teal.500" href="/login">
          Faça seu login
        </Link>
      </Box>
    </Flex>
  );
}
