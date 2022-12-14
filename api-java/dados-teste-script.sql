INSERT INTO public.users
(id, email, "name", "password", username)
VALUES(1, 'teste@email.com', 'Usuário de Teste', '$2a$10$ua.2Za6YglA6O440mucU4un4Kq/qB1pEXAaZ1mVKbfg1fUFwtAknO' /*teste123*/, 'teste');

INSERT INTO public.bank
(id, "name")
VALUES(1, 'Sicredi');
INSERT INTO public.bank
(id, "name")
VALUES(2, 'Nubank');
INSERT INTO public.bank
(id, "name")
VALUES(3, 'Banco do Brasil');
INSERT INTO public.bank
(id, "name")
VALUES(4, 'Itaú');

INSERT INTO public.category
(id, "name")
VALUES(1, 'Contas de casa');
INSERT INTO public.category
(id, "name")
VALUES(2, 'Transporte');
INSERT INTO public.category
(id, "name")
VALUES(3, 'Lazer');
INSERT INTO public.category
(id, "name")
VALUES(4, 'Salário');
INSERT INTO public.category
(id, "name")
VALUES(5, 'Transferência');

INSERT INTO public.account
(id, agency, description, "number", type_account, bank_id, user_id)
VALUES(1, '1235', 'Conta do Itaú', 458745, 'CC', 4, 1);
INSERT INTO public.account
(id, agency, description, "number", type_account, bank_id, user_id)
VALUES(2, '0001', 'Conta do Nubank', 9856231, 'CC', 2, 1);
INSERT INTO public.account
(id, agency, description, "number", type_account, bank_id, user_id)
VALUES(3, '0737', 'Conta do Sicredi', 2127455, 'CP', 1, 1);
INSERT INTO public.account
(id, agency, description, "number", type_account, bank_id, user_id)
VALUES(4, '0495', 'Conta do Banco do Brasil', 872135, 'CC', 3, 1);

INSERT INTO public.movement
(id, description, due_date, type_movement, value, account_id, category_id, destination_account_id)
VALUES(1, 'Salário', NULL, 'RECEITA', 10000.00, 2, 4, NULL);
INSERT INTO public.movement
(id, description, due_date, type_movement, value, account_id, category_id, destination_account_id)
VALUES(2, 'Conta de luz', NULL, 'DESPESA', 178.00, 2, 1, NULL);
INSERT INTO public.movement
(id, description, due_date, type_movement, value, account_id, category_id, destination_account_id)
VALUES(3, 'Transferência do nubank pro sicredi', NULL, 'TRANSFERENCIA_CONTAS', 5412.00, 2, 5, 3);
INSERT INTO public.movement
(id, description, due_date, type_movement, value, account_id, category_id, destination_account_id)
VALUES(4, 'Cheque que vai entrar', '2023-02-13 21:54:00.000', 'RECEITA', 20000.00, 4, 4, NULL);
INSERT INTO public.movement
(id, description, due_date, type_movement, value, account_id, category_id, destination_account_id)
VALUES(5, 'Transferência do banco do brasil pro itaú', NULL, 'TRANSFERENCIA_CONTAS', 4789.00, 4, 5, 1);
INSERT INTO public.movement
(id, description, due_date, type_movement, value, account_id, category_id, destination_account_id)
VALUES(6, 'Concerto casa', NULL, 'DESPESA', 245000.00, 4, 1, NULL);