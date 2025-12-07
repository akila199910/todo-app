import React, { useState } from "react";
import { createTask } from "../api";
import type { AxiosError } from "axios";

export default function NewTaskForm({ onCreated }: { onCreated: () => void }) {

  type ErrorType = {
    title?: string;
    description?: string;
  }
  const [title, setTitle] = useState("");
  const [desc, setDesc] = useState("");
  const [error, setError] = useState<null | ErrorType | string>(null);

  const submit = async (e: React.FormEvent) => {
    e.preventDefault();
    setError(null)

    try {
        const res = await createTask({ title, description: desc });
        console.log(res);
        setTitle("");
        setDesc("");
        onCreated();

    } catch (e: AxiosError | any) {
        console.log(error);
        if (e.response && e.response.data) {
            setError(e.response.data);
        } else {
            setError("An unexpected error occurred.");
        }

        console.log("Error creating task:", error);
    }
  };

  return (
    <form className="flex flex-col gap-4" onSubmit={submit}>
      <input
        className="border rounded-lg p-3 w-full"
        value={title}
        placeholder="Title"
        onChange={(e) => setTitle(e.target.value)}
      />
      {typeof error === "object" && error?.title && <div className="text-red-600">{error.title}</div>}

      <textarea
        className="border rounded-lg p-3 w-full h-28"
        value={desc}
        placeholder="Description"
        onChange={(e) => setDesc(e.target.value)}
      />
      {typeof error === "object" && error?.description && <div className="text-red-600">{error.description}</div>}


      <button
        type="submit"
        className="bg-blue-600 hover:bg-blue-700 text-white px-6 py-2 rounded-lg transition"
      >
        Add
      </button>
    </form>
  );
}
