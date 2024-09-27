import { describe, expect, it, vi } from "vitest";
import { DefaultTodoRepository } from "./TodoRepository.ts";
import axios from "axios";

describe("TodoRepository.tsx", () => {
  it("request for collect url when getTodos was called", async () => {
    const getAxiosSpy = vi
      .spyOn(axios, "get")
      .mockResolvedValue({ data: [{ id: 1, title: "title1" }] });
    const sut = new DefaultTodoRepository();

    const result = await sut.getTodos();

    expect(getAxiosSpy).toHaveBeenCalledWith("/api/todos");
    expect(result).toEqual([{ id: 1, title: "title1" }]);
  });
});
