import { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import {
  DefaultTodoRepository,
  TodoRepository,
} from "./Repository/TodoRepository.ts";

type Props = {
  repository?: TodoRepository;
};

const MainPage = ({ repository = new DefaultTodoRepository() }: Props) => {
  const navigate = useNavigate();
  const [todos, setTodos] = useState<Todo[]>([]);

  useEffect(() => {
    axios.get("/api/auth/me").catch(() => {
      navigate("/");
    });
  }, []);

  useEffect(() => {
    (async () => {
      const todos = await repository.getTodos();
      setTodos(todos);
    })();
    // repository.getTodos();
  }, []);

  return (
    <>
      <div>main page</div>
      <div>
        {todos.map((todo) => (
          <div key={todo.id}>{todo.title}</div>
        ))}
      </div>
    </>
  );
};

export default MainPage;
