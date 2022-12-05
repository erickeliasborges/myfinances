import { CheckIcon, CloseIcon } from "@chakra-ui/icons";
import { Box } from "@chakra-ui/react";
import { Link } from "react-router-dom";
import { CrudBody } from "../CrudBody";

interface ICrudMaintenance {
  title: string;
  onSubmitForm: () => void;
  linkCancelMaintenance: string;
  children: React.ReactNode;
}

export function CrudMaintenance({
  title,
  onSubmitForm,
  linkCancelMaintenance,
  children,
}: ICrudMaintenance) {
  return (
    <CrudBody title={`${title} / Manutenção`}>
      <form onSubmit={onSubmitForm}>
        {children}

        <Box display="flex" className="position-absolute bottom-0 end-0 m-3">
          {/* Botão cancelar */}
          <Link
            className="btn btn-danger rounded-circle btn-lg me-1"
            to={linkCancelMaintenance}
          >
            <CloseIcon></CloseIcon>
          </Link>
          {/* Botão salvar */}
          <button
            className="btn btn-success rounded-circle btn-lg"
            type="submit"
          >
            <CheckIcon></CheckIcon>
          </button>
        </Box>
      </form>
    </CrudBody>
  );
}
