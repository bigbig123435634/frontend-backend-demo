import React, { useState } from "react";

function DeleteArticle() {
  const [id, setId] = useState("");

  const [result, setResult] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await fetch(`http://localhost:8080/artical/${id}`, {
        method: "DELETE",
        headers: { "Content-Type": "application/json" },
      });

      const data = await response.json();

      setResult(data.isok ? "成功！" + data.message : "失败: " + data.message);

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
    </div>
  );
}

export default DeleteArticle;
