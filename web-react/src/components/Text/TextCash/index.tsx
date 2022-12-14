import { Container, Text } from "@chakra-ui/react";

interface ITextCash {
  text: number;
  fontSize: string;
  align: any;
  bg?: any;
}

export function TextCash({ text, fontSize, align, bg }: ITextCash) {
  const getNegativeColor = () => {
    if (text < 0) return "red";
  };
  return (
    <Text color={getNegativeColor()} bg={bg} fontSize={fontSize} align={align}>
      {text.toLocaleString("pt-br", { style: "currency", currency: "BRL" })}
    </Text>
  );
}
