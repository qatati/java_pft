package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.util.Set;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.SkipException;

public class TestBase {

  protected int createIssue(Issue newIssue) throws IOException {
    String json = getExecutor().execute(Request.Post("https://bugify.stqa.ru/api/issues.json")
        .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
            new BasicNameValuePair("description", newIssue.getDescription())))
        .returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    return parsed.getAsJsonObject().get("issue_id").getAsInt();
  }

  protected Set<Issue> getIssues() throws IOException {
    String json = getExecutor().execute(Request.Get("https://bugify.stqa.ru/api/issues.json"))
        .returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
  }

  private Executor getExecutor() {
    return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");
  }

  protected boolean isIssueOpen(int issueId) throws IOException {

    String status = getIssueStatus(issueId);
    return !"resolved".equals(status) || !"closed".equals(status);
  }

  private String getIssueStatus(int issueId) throws IOException {
    String json = getExecutor().execute(Request.Get("https://bugify.stqa.ru/api/issues/" + issueId + ".json"))
        .returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issuesJson = parsed.getAsJsonObject().get("issues");
    Set<Issue> issues = new Gson().fromJson(issuesJson, new TypeToken<Set<Issue>>(){}.getType());
    Issue issue = issues.iterator().next();
    return issue.getStatus();
  }

  public void skipIfNotFixed(int issueId) throws IOException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }
}
