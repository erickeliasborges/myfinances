import { Text } from '@chakra-ui/react';
import { ChangeEvent, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { ICategory } from '../../commons/interfaces';
import { ButtonWithProgress } from '../../components/ButtonWithProgress';
import { Input } from '../../components/Input';
import CategoryService from '../../service/CategoryService';


export function CategoryFormPage() {
    const [form, setForm] = useState({
        id: undefined,
        name: "",
    });
    const [errors, setErrors] = useState({
        id: undefined,
        name: "",
    });
    const [pendingApiCall, setPendingApiCall] = useState(false);
    const [apiError, setApiError] = useState(false);
    const navigate = useNavigate();


    const onChange = (event: ChangeEvent<HTMLInputElement>) => {
        const { value, name } = event.target;
        setForm((previousForm) => {
            return {
                ...previousForm,
                [name]: value,
            }
        });
        setErrors((previousErrors) => {
            return {
                ...previousErrors,
                [name]: '',
            }
        });
    }

    const onSubmit = () => {
        const category: ICategory = {
            name: form.name,
        }
        setPendingApiCall(true);
        CategoryService.save(category)
            .then((response) => {
                setPendingApiCall(false);
                navigate('/categories');
            })
            .catch((responseError) => {
                if (responseError.response.data.validationErrors) {
                    setErrors(responseError.response.data.validationErrors);
                }
                setPendingApiCall(false);
                setApiError(true);
            });
    }

    return (
        <div className="container">

            <Text className="text-center" fontSize='30px'>Categoria / Manutenção</Text>

            <div className="col-12 mb-3">
                <Input
                    className="form-control"
                    name="name"
                    label="Nome"
                    placeholder="Informe o nome"
                    type="text"
                    value={form.name}
                    onChange={onChange}
                    hasError={errors.name ? true : false}
                    error={errors.name}
                />
            </div>
            {apiError &&
                <div className="alert alert-danger">Falha ao cadastrar a categoria.</div>
            }
            <div className="text-center">
                <ButtonWithProgress
                    className="btn btn-primary"
                    onClick={onSubmit}
                    disabled={pendingApiCall ? true : false}
                    pendingApiCall={pendingApiCall}
                    text="Salvar"
                />
            </div>


        </div>
    )
}