Feature("akatask");

Scenario("login", ({ I }) => {
  I.amOnPage("/");
  I.fillField("input[name=id]", "akaishi@mail.com");
  I.fillField("input[name=password]", "akaishipassword");

  I.click("Login");

  I.seeInCurrentUrl("/main");
  I.see("main page");
  I.wait(1);
});
