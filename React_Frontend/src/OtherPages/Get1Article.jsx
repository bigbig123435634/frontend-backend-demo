import React, { useState } from "react";

function Get1Article() {
  const [id, setId] = useState("");
  const [articles, setArticles] = useState([]);
  const [result, setResult] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();

    setResult("");
    setArticles([]);

    try {
      const response = await fetch(`http://localhost:8080/artical/${id}`, {
        method: "GET",
        headers: { "Content-Type": "application/json" },
      });

      const data = await response.json();
      if (data.isok) {
        // 根据你的 AResponse 结构，文章在 data.data 中
        setArticles(data.data || []);
      } else {
        setResult("失败: " + data.message);
      }

      if (data.isok) {
        setId("");
      }
    } catch (error) {
      setResult("错误: " + error.message);
    }
  };

  return (
    <div>
      <h2>刪除文章</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <input
            type="number"
            value={id}
            onChange={(e) => setId(e.target.value)}
            placeholder="文章ID"
            required
          />
        </div>

        <button type="submit">提交</button>
      </form>
      {result && <p>{result}</p>}
      {articles && articles.id && (
        <div>
          <ul style={{ listStyle: "none", padding: 0 }}>
            <li
              key={articles.id}
              style={{
                border: "1px solid #ddd",
                margin: "10px 0",
                padding: "15px",
                borderRadius: "5px",
              }}
            >
              <h3 style={{ margin: "0 0 10px 0" }}>{articles.tittle}</h3>
              <p style={{ margin: "5px 0", color: "#666" }}>
                <strong>作者:</strong> {articles.author}
              </p>
              <p style={{ margin: "5px 0" }}>{articles.content}</p>
              <p
                style={{
                  margin: "5px 0",
                  fontSize: "0.9em",
                  color: "#999",
                }}
              >
                文章ID: {articles.id}
              </p>
            </li>
          </ul>
        </div>
      )}
    </div>
  );
}

export default Get1Article;
