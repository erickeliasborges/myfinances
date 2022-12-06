import { Routes, Route } from 'react-router-dom'
import { NavBar } from '../../components/NavBar'
import { AccountMaintenancePage } from '../../pages/Account/AccountMaintenancePage'
import { AccountSearchPage } from '../../pages/Account/AccountSearchPage'
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

                {/* Contas */}
                <Route path="/accounts" element={<AccountSearchPage />} />
                <Route path="/accounts/new" element={<AccountMaintenancePage />} />
                <Route path="/accounts/:id" element={<AccountMaintenancePage />} />

                <Route path="*" element={<HomePage />} />
            </Routes>
        </>
    )
}