

export interface Exam {
    id: number | null; // ID único do exame
    name: string | null; // Nome do exame
    requirements: string | null; // Requisitos do exame
    price: number | null; // Preço do exame
    analysisTime: number | null; // Tempo de análise em horas
}

export interface ExamTest {
    id: number | null; // ID único do teste de exame
    examId: number; // Detalhes do exame
    testResultId: number | null; // Resultado do teste
    status: string; // Status do teste
}

export interface TestResult {
    id: number | null;
    resultDate: string;
    resultContent: string;
}

export interface ExamRequest {
    examRequestId: number | null; // ID único da requisição de exame
    clientId: number; // Detalhes do cliente associado
    examTestList: string[]; // Lista de testes de exame
    requestDate: string; // Data da requisição no formato YYYY-MM-DD
    totalPrice: number; // Preço total da requisição
    paymentMethod: string; // Método de pagamento
    status: string; // Status da requisição
}

