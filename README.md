# Todo List API

이 프로젝트는 **Spring Boot**를 기반으로 한 간단한 Todo List API 애플리케이션입니다. 사용자는 이 API를 통해 할 일을 생성, 조회, 수정, 삭제할 수 있습니다.

---

## 프로젝트 개요

이 애플리케이션은 다음과 같은 주요 기능을 제공합니다:
- **할 일 생성** (Create)
- **할 일 목록 조회** (Read)
- **할 일 수정** (Update)
- **할 일 삭제** (Delete)

---

## 주요 기술 스택

- **Java 17**
- **Spring Boot 3.3**
- **AWS 데이터베이스 배포**
- **JPA** (Java Persistence API)

---

## 로컬에서 실행하기

1. 프로젝트를 클론합니다:

    ```bash
    git clone https://github.com/leeuihyoun/todolist.git
    ```
   
2. 데이터베이스 환경변수 설정:
    - `src/main/resources/application.yml`에서 **MYSQL_ID**와 **MYSQL_PWD**를 메일로 받은 값으로 설정해야 합니다.
    
    ```yaml
    username: ${MYSQL_ID}
    password: ${MYSQL_PWD}
    ```

3. 프로젝트 실행하기:
    
    ```bash
    ./gradlew bootrun
    ```

4. 애플리케이션이 실행되면, `http://localhost:8080`에서 API를 사용할 수 있습니다.

---

## 데이터베이스 스키마

H2 데이터베이스를 사용하며, 테이블 구조는 다음과 같습니다:

```sql
CREATE TABLE todos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    completed BOOLEAN NOT NULL,
    created_at DATETIME,
    updated_at DATETIME
);
```

## API 명세서

### 1. **할 일 목록 가져오기**

- **URL**: `/api/todos`
- **Method**: `GET`
- **설명**: 모든 할 일 목록을 가져옵니다.
- **응답 예시**:

    ```json
    [
        {
            "id": 1,
            "title": "할 일 1",
            "description": "할 일 1 설명",
            "completed": false,
            "created_at": "2023-09-09T12:30:00",
            "updated_at": "2023-09-10T14:00:00"
        },
        {
            "id": 2,
            "title": "할 일 2",
            "description": "할 일 2 설명",
            "completed": true,
            "created_at": "2023-09-08T11:00:00",
            "updated_at": "2023-09-09T15:30:00"
        }
    ]
    ```


### 2. **새 할 일 생성하기**

- **URL**: `/api/todos`
- **Method**: `POST`
- **설명**: 새로운 할 일을 생성합니다.
- **요청 본문**:

    ```json
    {
        "title": "새로운 할 일",
        "description": "새로운 할 일 설명",
        "completed": false,
        "created_at": "2023-09-09T12:30:00"
    }
    ```

- **응답 예시**:

    ```json
    {
        "id": 3,
        "title": "새로운 할 일",
        "description": "새로운 할 일 설명",
        "completed": false,
        "created_at": "2023-09-09T12:30:00"
    }
    ```

### 3. **할 일 수정하기**

- **URL**: `/api/todos/{id}`
- **Method**: `PUT`
- **설명**: 주어진 ID에 해당하는 할 일을 수정합니다.
- **요청 본문**:

    ```json
    {
        "title": "수정된 할 일 제목",
        "description": "수정된 할 일 설명",
        "completed": true,
        "updated_at": "2023-09-09T15:30:00"
    }
    ```

- **응답 예시**:

    ```json
    {
        "id": 1,
        "title": "수정된 할 일 제목",
        "description": "수정된 할 일 설명",
        "completed": true,
        "updated_at": "2023-09-09T15:30:00"
    }
    ```

### 5. **할 일 삭제하기**

- **URL**: `/api/todos/{id}`
- **Method**: `DELETE`
- **설명**: 주어진 ID에 해당하는 할 일을 삭제합니다.
- **응답**: `200 OK`

---


