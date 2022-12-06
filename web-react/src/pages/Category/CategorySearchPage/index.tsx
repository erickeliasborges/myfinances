import { useEffect, useState } from "react";
import { ICategory } from "../../../commons/interfaces";
import CategoryService from "../../../services/CategoryService";
import { IDataTableBody, CrudSearch } from "../../../components/Crud/CrudSearch";

export function CategorySearchPage() {
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

  const getColumnsWithDataTableBody = () => {
    let values: IDataTableBody[] = [];
    data.forEach((category: ICategory) => {
      let columns: string[] = [];
      let id: string = category.id!.toString();
      columns.push(id);
      columns.push(category.name);
      values.push({
        id: id,
        linkEditRegister: `/categories/${id}`,
        columns: columns,
        onClickRemove: onClickRemove,
      });
    });
    return values;
  };

  return (
    <CrudSearch
      title="Categorias"
      apiError={apiError}
      columsTableHead={["Id", "Nome"]}
      dataTableBody={getColumnsWithDataTableBody()}
      linkNewRegister="/categories/new"
    ></CrudSearch>
  );
}
