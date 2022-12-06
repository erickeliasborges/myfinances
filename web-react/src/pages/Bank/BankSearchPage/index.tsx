import { useEffect, useState } from "react";
import { ICategory } from "../../../commons/interfaces";
import BankService from "../../../services/BankService";
import {
  IDataTableBody,
  CrudSearch,
} from "../../../components/Crud/CrudSearch";

export function BankSearchPage() {
  const [data, setData] = useState([]);
  const [apiError, setApiError] = useState("");

  useEffect(() => {
    loadData();
  }, []);

  const loadData = () => {
    BankService.findAll()
      .then((response) => {
        setData(response.data);
        setApiError("");
      })
      .catch((responseError) => {
        setApiError("Falha ao carregar lista de bancos.");
      });
  };

  const onClickRemove = (id?: number) => {
    if (id) {
      BankService.remove(id)
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
    data.forEach((category: ICategory) => {
      let columns: string[] = [];
      let id: string = category.id!.toString();
      columns.push(id);
      columns.push(category.name);
      values.push({
        id: id,
        linkEditRegister: `/banks/${id}`,
        columns: columns,
        onClickRemove: onClickRemove,
      });
    });
    return values;
  };

  return (
    <CrudSearch
      title="Bancos"
      apiError={apiError}
      columsTableHead={["Id", "Nome"]}
      dataTableBody={getColumnsWithDataTableBody()}
      linkNewRegister="/banks/new"
    ></CrudSearch>
  );
}
