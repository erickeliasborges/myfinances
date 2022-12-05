import { Routes, Route } from 'react-router-dom'
import { NavBar } from '../../components/NavBar'
import { BankMaintenancePage } from '../../pages/Bank/BankMaintenancePage'
import { BankSearchPage } from '../../pages/Bank/BankSearchPage'
import { CategoryMaintenancePage } from '../../pages/Category/CategoryMaintenancePage'
import { CategorySearchPage } from '../../pages/Category/CategorySearchPage'
import { HomePage } from '../../pages/HomePage'

export function AuthenticatedRoutes() {
    return (
        <>
            <NavBar />
            <Routes>
                <Route path="/" element={<HomePage />} />

                {/* Categorias */}
                <Route path="/categories" element={<CategorySearchPage />} />
                <Route path="/categories/new" element={<CategoryMaintenancePage />} />
                <Route path="/categories/:id" element={<CategoryMaintenancePage />} />

                {/* Bancos */}
                <Route path="/banks" element={<BankSearchPage />} />
                <Route path="/banks/new" element={<BankMaintenancePage />} />
                <Route path="/banks/:id" element={<BankMaintenancePage />} />

                <Route path="*" element={<HomePage />} />
            </Routes>
        </>
    )
}