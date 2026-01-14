package org.noa.hospitaleo.enums;

public enum APIKeys {

    DATABASE_URL("jdbc:h2:tcp://localhost/~/Java-2025"),
    DATABASE_USERNAME("student"),
    DATABASE_PASSWORD("student");

    private  String key;

  APIKeys(String key)
  {
      this.key=key;
  }

  public String getKey()
  {
      return key;
  }
}
