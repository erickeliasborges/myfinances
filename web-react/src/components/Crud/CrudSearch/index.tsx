import { Link } from "react-router-dom";
import {
  Table,
  Thead,
  Tbody,
  Tr,
  Th,
  Td,
  TableContainer,
  Box,
} from "@chakra-ui/react";
import { AddIcon, DeleteIcon, EditIcon } from "@chakra-ui/icons";
import { CrudBody } from "../CrudBody";

export interface IDataTableBody {
  id: string,
  linkEditRegister: string,
  columns: string[];
  onClickRemove: (id?: number) => void;
}

interface ICrudSearch {
  title: string;
  apiError: string;
  columsTableHead: string[];
  dataTableBody: IDataTableBody[];
  linkNewRegister: string;
}

export function CrudSearch({
  title,
  apiError,
  columsTableHead,
  dataTableBody,
  linkNewRegister,
}: ICrudSearch) {
  return (
    <CrudBody title={`${title} / Pesquisa`}>
      <TableContainer
        overflowY="auto"
        maxH="75%"
        //Estiliza a barra de rolagem
        css={{
          "&::-webkit-scrollbar": {
            width: "4px",
          },
          "&::-webkit-scrollbar-track": {
            width: "6px",
          },
          "&::-webkit-scrollbar-thumb": {
            background: "gray",
            borderRadius: "24px",
          },
        }}
      >
        <Table variant="striped" colorScheme="gray">
          <Thead>
            <Tr>
              {columsTableHead.map((value: any) => {
                return <Th key={value}>{value}</Th>;
              })}
              {/* Header para os botões de ação */}
              <Th></Th>
            </Tr>
          </Thead>
          <Tbody>
            {/* Faz um map para adicionar os dados do body na tabela */}
            {dataTableBody.map((value: IDataTableBody) => {
              return (
                <Tr key={value.id}>
                  {/* Faz um map para adicionar os dados conforme a coluna do head da tabela */}
                  {value.columns.map((value) => {
                    return <Td>{value}</Td>;
                  })}
                  {/* Botões de ação de cada linha da tabela */}
                  <Td className="d-flex justify-content-end">
                    <Link
                      className="btn btn-primary me-1 rounded-circle"
                      to={value.linkEditRegister}
                    >
                      <EditIcon></EditIcon>
                    </Link>
                    <button
                      className="btn btn-danger rounded-circle"
                      onClick={() => value.onClickRemove(Number(value.id))}
                    >
                      <DeleteIcon></DeleteIcon>
                    </button>
                  </Td>
                </Tr>
              );
            })}
          </Tbody>
        </Table>
      </TableContainer>
      {apiError && <div className="alert alert-danger">{apiError}</div>}

      {/* Botão para adicionar novo registro */}
      <Box display="flex" className="position-absolute bottom-0 end-0 m-3">
        <Link
          className="btn btn-success rounded-circle btn-lg"
          to={linkNewRegister}
        >
          <AddIcon></AddIcon>
        </Link>
      </Box>
    </CrudBody>
  );
}
