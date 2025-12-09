import { render, screen, fireEvent, waitFor } from "@testing-library/react";
import TaskList from "../components/TaskList";
import * as api from "../api";

describe("TaskList", () => {
  const sampleTasks = [
    { id: 1, title: "Task 1", description: "Desc 1" },
  ];

  it("renders tasks", () => {
    render(<TaskList tasks={sampleTasks} onDone={() => {}} />);

    expect(screen.getByText("Task 1")).toBeInTheDocument();
    expect(screen.getByText("Desc 1")).toBeInTheDocument();
  });

  it("calls markDone and then onDone", async () => {
    const mockDone = vi.spyOn(api, "markDone").mockResolvedValue({});
    const onDone = vi.fn();

    render(<TaskList tasks={sampleTasks} onDone={onDone} />);

    fireEvent.click(screen.getByText("Done"));

    await waitFor(() => expect(mockDone).toHaveBeenCalledWith(1));
    expect(onDone).toHaveBeenCalled();
  });
});
