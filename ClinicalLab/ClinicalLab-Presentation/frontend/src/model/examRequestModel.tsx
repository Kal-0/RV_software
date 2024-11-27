import { Client } from './clientModel';

export interface Exam {
    id: number | null; // ID único do exame
    name: string | null; // Nome do exame
    requirements: string | null; // Requisitos do exame
    price: number | null; // Preço do exame
    analysisTime: number | null; // Tempo de análise em horas
}

export interface ExamTest {
    examTestId: number | null; // ID único do teste de exame
    exam: Exam; // Detalhes do exame
    testResult: string | null; // Resultado do teste
    status: string | null; // Status do teste
}

export interface ExamRequest {
    examRequestId: number | null; // ID único da requisição de exame
    client: Client; // Detalhes do cliente associado
    examTestList: ExamTest[]; // Lista de testes de exame
    requestDate: string; // Data da requisição no formato YYYY-MM-DD
    totalPrice: number; // Preço total da requisição
    paymentMethod: string; // Método de pagamento
    status: string; // Status da requisição
}

// Exemplo de um ExamRequest inicial vazio para usar como valor padrão
export const defaultExamRequest: ExamRequest = {
    examRequestId: null,
    client: {
        id: null,
        cpf: '',
        contactEmail: '',
        name: '',
        birthDate: '',
        clientId: 0,
    },
    examTestList: [],
    requestDate: '', // Data vazia
    totalPrice: 0.0, // Preço padrão
    paymentMethod: '', // Método de pagamento vazio
    status: '', // Status vazio
};
