import { Container, Text } from "@chakra-ui/react";

interface ICrudBody {
  title: string;
  children: React.ReactNode;
}

export function CrudBody({ title, children }: ICrudBody) {
  return (
    <Container position="absolute" height="90%" maxW="100%">
      <Text className="text-center p-2" fontSize="30px">
        {title}
      </Text>      
      {children}      
    </Container>
  );
}
