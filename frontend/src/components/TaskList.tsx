import { type Task, markDone } from "../api";

export default function TaskList({
  tasks,
  onDone,
}: {
  tasks: Task[];
  onDone: () => void;
}) {


  
  const handleDone = async (id: number) => {

    try {
      const res = await markDone(id);
      console.log("Task marked as done:", res);
      onDone();
    } catch (error) {
      console.log("Error marking task as done:", error);
    }
  };

  return (
    <div className="flex flex-col gap-4">
      {tasks.map((t) => (
        <div
          key={t.id}
          className="bg-gray-200 rounded-lg p-5 shadow-sm flex justify-between items-center"
        >
          <div>
            <h3 className="font-semibold text-lg">{t.title}</h3>
            <p className="text-gray-600 text-sm">{t.description}</p>
          </div>

          <button
            onClick={() => t.id !== undefined && handleDone(t.id)}
            className="border border-gray-500 px-4 py-1 rounded-lg hover:bg-gray-300 transition"
          >
            Done
          </button>
        </div>
      ))}
    </div>
  );
}
