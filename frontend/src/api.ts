import axios from "axios";

const API = axios.create({
    baseURL: import.meta.env.VITE_API_URL ?? "http://localhost:8080/api",
});

export type Task = {
    id?: number;
    title: string;
    description?: string;
    createdAt?: string;
    completed?: boolean;
};

export const taskList = async () => {
    const res = await API.get<Task[]>("/tasks");
    return res;
};

export const createTask = async (payload: {title: string; description?: string}) => {
       const res = await API.post<Task>("/tasks", payload);
       return res;
};

export const markDone = async (id: number) => {
    const res = await API.post(`/tasks/${id}/done`);
    return res;
};
