import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { ICategory } from "../../commons/interfaces";
import CategoryService from "../../service/CategoryService";
import {
  Table,
  Thead,
  Tbody,
  Tfoot,
  Tr,
  Th,
  Td,
  TableCaption,
  TableContainer,
  Button,
  Container,
  Box,
  Text,
} from "@chakra-ui/react";
import { AddIcon, DeleteIcon, EditIcon } from "@chakra-ui/icons";

export function CategoryListPage() {
  const [data, setData] = useState([]);
  const [apiError, setApiError] = useState("");

  useEffect(() => {
    loadData();
  }, []);

  const loadData = () => {
    CategoryService.findAll()
      .then((response) => {
        setData(response.data);
        setApiError("");
      })
      .catch((responseError) => {
        setApiError("Falha ao carregar lista de categorias.");
      });
  };

  const onClickRemove = (id?: number) => {
    if (id) {
      CategoryService.remove(id)
        .then((response) => {
          loadData();
          setApiError("");
        })
        .catch((responseError) => {
          setApiError("Falha ao remover o registro.");
        });
    }
  };

  return (    

      <Container position="absolute" height="90%" maxW="100%">
      <Text className="text-center p-2" fontSize="30px">
        Categoria / Pesquisa
      </Text>
        <TableContainer overflowY='auto' maxH='75%'
        //Estiliza a barra de rolagem
        css={{
          '&::-webkit-scrollbar': {
            width: '4px',
          },
          '&::-webkit-scrollbar-track': {
            width: '6px',
          },
          '&::-webkit-scrollbar-thumb': {
            background: 'gray',
            borderRadius: '24px',
          },
        }}>
          <Table variant="striped" colorScheme="gray">
            <Thead>
              <Tr>
                <Th>Id</Th>
                <Th>Nome</Th>
                <Th></Th>
              </Tr>
            </Thead>
            <Tbody>
              {data.map((category: ICategory) => (
                <Tr key={category.id}>
                  <Td>{category.id}</Td>
                  <Td>{category.name}</Td>
                  <Td className="d-flex justify-content-end">
                    <Link
                      className="btn btn-primary me-1 rounded-circle"
                      to={`/categories/${category.id}`}
                    >
                      <EditIcon></EditIcon>
                    </Link>
                    <button
                      className="btn btn-danger rounded-circle"
                      onClick={() => onClickRemove(category.id)}
                    >
                      <DeleteIcon></DeleteIcon>
                    </button>
                  </Td>
                </Tr>
              ))}
            </Tbody>
          </Table>
        </TableContainer>
        {apiError && <div className="alert alert-danger">{apiError}</div>}

        <Box display="flex" className="position-absolute bottom-0 end-0 m-3">
          <Link
            className="btn btn-success rounded-circle btn-lg"
            to="/categories/new"
          >
            <AddIcon></AddIcon>
          </Link>
        </Box>
      </Container>

  );
}
