import { Routes, Route } from 'react-router-dom'
import { NavBar } from '../../components/NavBar'
import { CategoryMaintenancePage } from '../../pages/Category/CategoryMaintenancePage'
import { CategorySearchPage } from '../../pages/Category/CategorySearchPage'
import { HomePage } from '../../pages/HomePage'

export function AuthenticatedRoutes() {
    return (
        <>
            <NavBar />
            <Routes>
                <Route path="/" element={<HomePage />} />

                <Route path="/categories" element={<CategorySearchPage />} />
                <Route path="/categories/new" element={<CategoryMaintenancePage />} />
                <Route path="/categories/:id" element={<CategoryMaintenancePage />} />

                <Route path="*" element={<HomePage />} />
            </Routes>
        </>
    )
}