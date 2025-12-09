import { render, screen, waitFor } from "@testing-library/react";
import App from "../App";
import * as api from "../api";

describe("App integration test", () => {
  it("loads and displays tasks", async () => {
    vi.spyOn(api, "taskList").mockResolvedValue({
      data: [{ id: 1, title: "Loaded Task", description: "Loaded Desc" }],
    });

    render(<App />);

    await waitFor(() =>
      expect(screen.getByText("Loaded Task")).toBeInTheDocument()
    );
  });
});
