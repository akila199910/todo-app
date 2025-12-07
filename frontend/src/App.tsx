import { useEffect, useState } from "react";
import NewTaskForm from "./components/NewTaskForm";
import TaskList from "./components/TaskList";
import { taskList, type Task } from "./api";

export default function App() {
  const [tasks, setTasks] = useState<Task[]>([]);

  const load = async ()=>{
    try {
      const res = await taskList();
      setTasks(res.data);
    } catch (error) {

      console.log(error);
      
    }
  }

  useEffect(() => {
    load();
  }, []);

  return (
    <div className="min-h-screen bg-gray-100 flex items-center justify-center p-6">
      <div className="bg-white shadow-xl rounded-3xl w-full max-w-6xl p-10">
        <div className="grid grid-cols-1 md:grid-cols-2 gap-10">

          <div>
            <h2 className="text-xl font-semibold mb-4">Add a Task</h2>
            <NewTaskForm onCreated={load} />
          </div>

          <div className="border-l pl-10">
            <TaskList tasks={tasks} onDone={load} />
          </div>

        </div>
      </div>
    </div>
  );
}
