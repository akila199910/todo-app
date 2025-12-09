import { render, screen, fireEvent, waitFor } from "@testing-library/react";
import NewTaskForm from "../components/NewTaskForm";
import * as api from "../api";

describe("NewTaskForm", () => {
  it("renders form fields", () => {
    render(<NewTaskForm onCreated={() => {}} />);

    expect(screen.getByPlaceholderText("Title")).toBeInTheDocument();
    expect(screen.getByPlaceholderText("Description")).toBeInTheDocument();
  });

  it("submits the form and calls onCreated", async () => {
    const mockCreate = vi.spyOn(api, "createTask").mockResolvedValue({ data: {} });
    const onCreated = vi.fn();

    render(<NewTaskForm onCreated={onCreated} />);

    fireEvent.change(screen.getByPlaceholderText("Title"), {
      target: { value: "Test Task" },
    });

    fireEvent.change(screen.getByPlaceholderText("Description"), {
      target: { value: "Test Desc" },
    });

    fireEvent.click(screen.getByText("Add"));

    await waitFor(() => expect(mockCreate).toHaveBeenCalled());
    expect(onCreated).toHaveBeenCalled();
  });

  it("shows validation errors from backend", async () => {
    vi.spyOn(api, "createTask").mockRejectedValue({
      response: { data: { title: "Title is required" } },
    });

    render(<NewTaskForm onCreated={() => {}} />); 

    fireEvent.click(screen.getByText("Add"));

    await waitFor(() =>
      expect(screen.getByText("Title is required")).toBeInTheDocument()
    );
  });
});
