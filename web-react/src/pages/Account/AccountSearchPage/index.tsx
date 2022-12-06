import { useEffect, useState } from "react";
import { IAccount } from "../../../commons/interfaces";
import AccountService from "../../../services/AccountService";
import {
  IDataTableBody,
  CrudSearch,
} from "../../../components/Crud/CrudSearch";
import AuthService from "../../../services/AuthService";

export function AccountSearchPage() {
  const [data, setData] = useState([]);
  const [apiError, setApiError] = useState("");

  useEffect(() => {
    loadData();
  }, []);

  const loadData = () => {
    // TODO: ajustar para guardar o usuário logado na hora que faz login, ajustar nas demais telas que usam o AuthService.loggedUser()
    AuthService.loggedUser()
      .then((response: any) => {
        AccountService.findByUserId(response.data.id)
          .then((response) => {
            setData(response.data);
            setApiError("");
          })
          .catch((responseError) => {
            setApiError("Falha ao carregar lista de contas.");
          });
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const onClickRemove = (id?: number) => {
    if (id) {
      AccountService.remove(id)
        .then((response) => {
          loadData();
          setApiError("");
        })
        .catch((responseError) => {
          setApiError("Falha ao remover o registro.");
        });
    }
  };

  const getColumnsWithDataTableBody = () => {
    let values: IDataTableBody[] = [];
    data.forEach((account: IAccount) => {
      let columns: string[] = [];
      let id: string = account.id!.toString();
      columns.push(id); // id
      columns.push(account.agency); // Agência
      columns.push(account.number.toString()); // Número
      columns.push(account.bank.name); // Banco
      columns.push(account.typeAccount.toString()); // Tipo
      values.push({
        id: id,
        linkEditRegister: `/accounts/${id}`,
        columns: columns,
        onClickRemove: onClickRemove,
      });
    });
    return values;
  };

  return (
    <CrudSearch
      title="Contas"
      apiError={apiError}
      columsTableHead={["Id", "Agência", "Número", "Banco", "Tipo"]}
      dataTableBody={getColumnsWithDataTableBody()}
      linkNewRegister="/accounts/new"
    ></CrudSearch>
  );
}
