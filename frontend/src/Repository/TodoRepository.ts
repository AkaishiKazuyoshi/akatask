import axios from "axios";

export interface TodoRepository {
  getTodos(): Promise<Todo[]>;
}

export class DefaultTodoRepository implements TodoRepository {
  async getTodos(): Promise<Todo[]> {
    const todos = await axios.get("/api/todos");
    return todos.data;
  }
}
