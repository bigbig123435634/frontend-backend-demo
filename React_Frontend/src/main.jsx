import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import "./index.css";
import App from "./OtherPages/App.jsx";

import NotfindPage from "./StaticCompent/NotfindPage.jsx";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import Get1Atriale from "./OtherPages/get1Article.jsx";
import PostArtical from "./OtherPages/postArtical.jsx";
import PutArticle from "./OtherPages/PutArticle.jsx";
import DeleteArticle from "./OtherPages/DeleteArticle.jsx";

const router = createBrowserRouter([
  { path: "/get", element: <App /> },
  { path: "/get/id", element: <Get1Atriale /> },
  { path: "/post", element: <PostArtical /> },
  { path: "/put", element: <PutArticle /> },
  { path: "/delete", element: <DeleteArticle /> },

  { path: "*", element: <NotfindPage /> },
]);

createRoot(document.getElementById("root")).render(
  <StrictMode>
    <RouterProvider router={router} />
  </StrictMode>
);
