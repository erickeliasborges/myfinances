import { useEffect, useState } from "react";
import { ICategory, IMovement } from "../../../commons/interfaces";
import MovementService from "../../../services/MovementService";
import { IDataTableBody, CrudSearch } from "../../../components/Crud/CrudSearch";

export function MovementSearchPage() {
  const [data, setData] = useState([]);
  const [apiError, setApiError] = useState("");

  useEffect(() => {
    loadData();
  }, []);

  const loadData = () => {
    MovementService.findAll()
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
      MovementService.remove(id)
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
    data.forEach((movement: IMovement) => {
      let columns: string[] = [];
      let id: string = movement.id!.toString();
      columns.push(id);
      columns.push(`${movement.account.agency} - ${movement.account.number} - ${movement.account.bank.name}`);
      values.push({
        id: id,
        linkEditRegister: `/movements/${id}`,
        columns: columns,
        onClickRemove: onClickRemove,
      });
    });
    return values;
  };

  return (
    <CrudSearch
      title="Movimentações"
      apiError={apiError}
      columsTableHead={["Id", "Conta"]}
      dataTableBody={getColumnsWithDataTableBody()}
      linkNewRegister="/movements/new"
    ></CrudSearch>
  );
}
