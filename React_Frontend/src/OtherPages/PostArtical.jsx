import React, { useState } from "react";

function PostArtical() {
  const [tittle, setTitle] = useState("");
  const [content, setContent] = useState("");
  const [author, setAuthor] = useState("");

  const [result, setResult] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await fetch("http://localhost:8080/artical", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ author, tittle, content }),
      });

      const data = await response.json();
      setResult(data.isok ? "成功！" + data.message : "失败: " + data.message);

      if (data.isok) {
        setTitle("");
        setContent("");
        setAuthor("");
      }
    } catch (error) {
      setResult("错误: " + error.message);
    }
  };

  return (
    <div>
      <h2>创建文章</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <textarea
            value={author}
            onChange={(e) => setAuthor(e.target.value)}
            placeholder="作者"
            required
          />
        </div>
        <div>
          <input
            value={tittle}
            onChange={(e) => setTitle(e.target.value)}
            placeholder="標題"
            required
          />
        </div>
        <div>
          <textarea
            value={content}
            onChange={(e) => setContent(e.target.value)}
            placeholder="内容"
            required
          />
        </div>
        <button type="submit">提交</button>
      </form>
      {result && <p>{result}</p>}
    </div>
  );
}

export default PostArtical;
