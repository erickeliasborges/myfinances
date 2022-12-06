import { Container, Text } from "@chakra-ui/react";
import React, { useEffect, useState } from "react";
import AuthService from "../../services/AuthService";

export function HomePage() {
    const [userName, setUserName] = useState("");

  const getName = () => {
    AuthService.loggedUser()
      .then((user: any) => {
        setUserName(user.data.name);
      })
      .catch((error) => {
        console.log(error);
        setUserName("");
      });
  };

  useEffect(() => {
    getName();
  }, []);
  
    return (
      <Container position="absolute" height="90%" maxW="100%">
        <Text className="text-center p-2" fontSize="30px">
          Olá, {userName}!
        </Text>
      </Container>
    );

}
