import { useState, useEffect } from "react";
import axios from "axios";

function App() {
  const [articles, setArticles] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  // 获取所有文章
  const fetchArticles = async () => {
    try {
      setLoading(true);
      // 直接调用你的后端 API
      const response = await fetch("http://localhost:8080/artical", {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        },
        //body: JSON.stringify({
        //  title: "新文章",
        //  content: "内容",
        // }),
      });

      //or 用 axios
      //const response2 = await axios.get("/artical");

      const data = await response.json();

      console.log("后端返回的数据:", data); // 调试用

      if (data.isok) {
        // 根据你的 AResponse 结构，文章在 data.data 中
        setArticles(data.data || []);
      } else {
        setError(data.message || "获取文章失败");
      }
    } catch (err) {
      setError("无法连接到后端服务，请确保后端正在运行");
      console.error("获取文章失败:", err);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchArticles();
  }, []);

  return (
    <>
      <div style={{ padding: "20px", fontFamily: "Arial, sans-serif" }}>
        <h1 style={{ color: "#333" }}>文章列表</h1>

        {error && (
          <div
            style={{
              backgroundColor: "#fee",
              color: "#c33",
              padding: "10px",
              margin: "10px 0",
              borderRadius: "5px",
            }}
          >
            错误: {error}
          </div>
        )}

        {loading ? (
          <p>加载中...</p>
        ) : articles.length === 0 ? (
          <p>没有文章</p>
        ) : (
          <div>
            <p>找到 {articles.length} 篇文章:</p>
            <ul style={{ listStyle: "none", padding: 0 }}>
              {articles.map((article) => (
                <li
                  key={article.id}
                  style={{
                    border: "1px solid #ddd",
                    margin: "10px 0",
                    padding: "15px",
                    borderRadius: "5px",
                  }}
                >
                  <h3 style={{ margin: "0 0 10px 0" }}>
                    {article.tittle || "无标题"}
                  </h3>
                  <p style={{ margin: "5px 0", color: "#666" }}>
                    <strong>作者:</strong> {article.author || "未知"}
                  </p>
                  <p style={{ margin: "5px 0" }}>
                    {article.content || "无内容"}
                  </p>
                  <p
                    style={{
                      margin: "5px 0",
                      fontSize: "0.9em",
                      color: "#999",
                    }}
                  >
                    文章ID: {article.id}
                  </p>
                </li>
              ))}
            </ul>
          </div>
        )}

        <div
          style={{
            marginTop: "30px",
            padding: "10px",
            backgroundColor: "#f5f5f5",
            borderRadius: "5px",
          }}
        >
          <p>
            <strong>后端API:</strong> http://localhost:8080/artical
          </p>
          <p>
            <strong>响应格式:</strong>{" "}
            {"{ isok: boolean, code: number, message: string, data: array }"}
          </p>
          <button
            onClick={fetchArticles}
            style={{
              marginTop: "10px",
              padding: "8px 16px",
              backgroundColor: "#007bff",
              color: "white",
              border: "none",
              borderRadius: "4px",
              cursor: "pointer",
            }}
          >
            刷新列表
          </button>
        </div>
      </div>
    </>
  );
}

export default App;
