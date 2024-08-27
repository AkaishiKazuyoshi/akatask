export interface TodoRepository {
  getTodos(): Promise<Todo[]>;
}

export class DefaultTodoRepository implements TodoRepository {
  async getTodos(): Promise<Todo[]> {
    return [];
  }
}
