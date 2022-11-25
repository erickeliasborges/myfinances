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
import { ChangeEvent, useState } from "react";
import { useForm } from "react-hook-form";
import { IUserLogin } from "../../commons/interfaces";
import AuthService from "../../service/AuthService";

export function LoginPage() {
  const {
    handleSubmit,
    register,
    formState: { errors, isSubmitting },
    reset,
  } = useForm<IUserLogin>();

  const [form, setForm] = useState<IUserLogin>({
    username: "",
    password: "",
  });

  const [pendingApiCall, setPendingApiCall] = useState(false);
  const [apiError, setApiError] = useState("");

  const onChange = (event: ChangeEvent<HTMLInputElement>) => {
    const { value, name } = event.target;
    setForm((previousForm) => {
      return {
        ...previousForm,
        [name]: value,
      };
    });
  };

  const onSubmit = () => {
    setPendingApiCall(true);
    const userLogin: IUserLogin = {
      username: form.username,
      password: form.password,
    };
    AuthService.login(userLogin)
      .then((response) => {
        localStorage.setItem("token", JSON.stringify(response.data.token));
        setPendingApiCall(false);
        window.location.reload();
        console.log(response);
      })
      .catch((errorResponse) => {
        setApiError((errorResponse.response && errorResponse.response.status === 401 ? "Usuário ou senha inválidos" : "Falha ao efetuar login"));
        setPendingApiCall(false);
        console.log(errorResponse);
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
        <Heading color="teal.400">Login</Heading>
        <Box minWidth={{ base: "90%", md: "468px" }}>
          <form onSubmit={handleSubmit(onSubmit)}>
            <Stack
              spacing={4}
              p="1rem"
              backgroundColor="whiteAlpha.900"
              boxShadow="md"
            >
              <FormControl
                variant="floating"
                id="username"
                isInvalid={errors.username && true}
              >
                <Input
                  placeholder=" "
                  type="text"
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
                <FormErrorMessage>
                  {errors.password && errors.password.message}
                </FormErrorMessage>
                <FormLabel>Senha</FormLabel>
              </FormControl>

              {apiError && (
                <div className="alert alert-danger text-center">{apiError}</div>
              )}

              <Button
                borderRadius={0}
                type="submit"
                variant="solid"
                colorScheme="teal"
                width="full"
              >
                Login
              </Button>
            </Stack>
          </form>
        </Box>
      </Stack>
      <Box>
        Não tem cadastro?{" "}
        <Link color="teal.500" href="/signup">
          Cadastre-se
        </Link>
      </Box>
    </Flex>
  );
}
