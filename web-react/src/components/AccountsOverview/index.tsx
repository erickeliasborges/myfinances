import {
  Card,
  CardBody,
  CardHeader,
  Heading,
  SimpleGrid,
  Text,
} from "@chakra-ui/react";
import { useEffect, useState } from "react";
import { IOverview } from "../../commons/interfaces";
import { TextCash } from "../../components/Text/TextCash";
import AccountService from "../../services/AccountService";

export function AccountsOverview() {
  const [overview, setOverview] = useState([]);

  useEffect(() => {
    AccountService.getOverview().then((response) => {
      setOverview(response.data);
    });
  }, []);

  const getColorLine = () => {
    return "gray.100";
  };

  return (
    <SimpleGrid spacing={4} templateColumns="repeat(auto-fill, minmax(500px, 1fr))">
      {overview.map((overview: IOverview) => (
        <Card align="start">
          <CardHeader alignSelf="center">
            <Heading size="md">{overview.account.description}</Heading>
            <Text align="center">
              {overview.account.agency} - {overview.account.number} -{" "}
              {overview.account.bank.name}
            </Text>
          </CardHeader>
          <CardBody alignSelf="center">
            <SimpleGrid columns={2}>
              <Text bg={getColorLine()} fontSize="sm">
                Saldo
              </Text>
              <TextCash
                bg={getColorLine()}
                text={overview.saldo}
                fontSize="sm"
                align="end"
              ></TextCash>

              <Text fontSize="sm">Saldo previsto</Text>
              <TextCash
                text={overview.saldoPrevisto}
                fontSize="sm"
                align="end"
              ></TextCash>

              <Text bg={getColorLine()} fontSize="sm">
                Receitas
              </Text>
              <TextCash
                bg={getColorLine()}
                text={overview.receitas}
                fontSize="sm"
                align="end"
              ></TextCash>

              <Text fontSize="sm">Receitas previstas</Text>
              <TextCash
                text={overview.receitasPrevistas}
                fontSize="sm"
                align="end"
              ></TextCash>

              <Text bg={getColorLine()} fontSize="sm">
                Despesas
              </Text>
              <TextCash
                bg={getColorLine()}
                text={overview.despesas}
                fontSize="sm"
                align="end"
              ></TextCash>

              <Text fontSize="sm">Despesas previstas</Text>
              <TextCash
                text={overview.despesasPrevistas}
                fontSize="sm"
                align="end"
              ></TextCash>

              <Text bg={getColorLine()} fontSize="sm">
                Transferências recebidas
              </Text>
              <TextCash
                bg={getColorLine()}
                text={overview.transferenciasRecebidas}
                fontSize="sm"
                align="end"
              ></TextCash>

              <Text fontSize="sm">Transferências recebidas previstas</Text>
              <TextCash
                text={overview.transferenciasRecebidasPrevistas}
                fontSize="sm"
                align="end"
              ></TextCash>

              <Text bg={getColorLine()} fontSize="sm">
                Transferências enviadas
              </Text>
              <TextCash
                bg={getColorLine()}
                text={overview.transferenciasEnviadas}
                fontSize="sm"
                align="end"
              ></TextCash>

              <Text fontSize="sm">Transferências enviadas previstas</Text>
              <TextCash
                text={overview.transferenciasEnviadasPrevistas}
                fontSize="sm"
                align="end"
              ></TextCash>
            </SimpleGrid>
          </CardBody>
        </Card>
      ))}
    </SimpleGrid>
  );
}
