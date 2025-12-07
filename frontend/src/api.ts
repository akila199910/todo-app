import axios from 'axios';

const API = axios.create({ 
    baseURL: import.meta.env.VITE_API_URL ?? 
    'http://localhost:8080/api' 
});

export type Task = { 
                id?: number; 
                title: string; 
                description?: string; 
                createdAt?: string; 
                completed?: boolean 
            };

export const list = () => API.get<Task[]>('/tasks').then(r=>r.data);

export const create = (t: {title:string, description?:string})=>API.post<Task>('/tasks', t).then(r=>r.data);

export const markDone = (id:number)=>API.post(`/tasks/${id}/done`);