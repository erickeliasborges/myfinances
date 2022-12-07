import { useEffect, useState } from "react";
import { IMovement } from "../../../commons/interfaces";
import MovementService from "../../../services/MovementService";
import {
  IDataTableBody,
  CrudSearch,
} from "../../../components/Crud/CrudSearch";
import {
  getEnumByKey,
  TypeMovementEnum,
} from "../../../commons/enums";
import { formatDatePtBr, formatMoneyPtBr } from "../../../utils/FormatUtils";

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
      columns.push(movement.description); // Descrição
      columns.push(getEnumByKey(TypeMovementEnum, movement.typeMovement)); // Tipo      
      columns.push(formatMoneyPtBr(movement.value)!); // Valor
      columns.push(formatDatePtBr(movement.dueDate)!); // Data de vencimento
      columns.push(
        `${movement.account.agency} - ${movement.account.number} - ${movement.account.bank.name}`
      ); // Conta
      columns.push( movement.destinationAccount ? 
        `${movement.destinationAccount.agency} - ${movement.destinationAccount.number} - ${movement.destinationAccount.bank.name}` : ""
      ); // Conta
      columns.push(movement.category.name); // Categoria
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
      columsTableHead={[
        "Descrição",
        "Tipo",        
        "Valor",
        "Data de vencimento",
        "Conta",
        "Conta destino",
        "Categoria",
      ]}
      dataTableBody={getColumnsWithDataTableBody()}
      linkNewRegister="/movements/new"
    ></CrudSearch>
  );
}
