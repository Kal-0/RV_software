export interface Client {
    id: number | null; // ID único do cliente, pode ser null antes de ser gerado
    cpf: string; // CPF do cliente com máscara
    contactEmail: string; // E-mail de contato do cliente
    name: string; // Nome completo do cliente
    birthDate: string; // Data de nascimento no formato YYYY-MM-DD
    clientId: number; // ID específico do cliente
}

// Exemplo de um cliente inicial vazio para usar como valor padrão
export const defaultClient: Client = {
    id: null,
    cpf: '', // CPF vazio
    contactEmail: '', // E-mail vazio
    name: '', // Nome vazio
    birthDate: '', // Data de nascimento vazia
    clientId: 0, // ID padrão
};
