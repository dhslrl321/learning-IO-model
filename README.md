# http server 를 만들어보며 배우는 I/O model

I/O model 은 sync/async 를 기준으로 크게 4가지로 나눌 수 있습니다.

- Synchronous model
  - blocking: **Blocking I/O**
  - non-blocking: **Non-Blocking I/O**
- Asynchronous model
  - blocking: **I/O-Multiplexing**
  - non-blocking: **AIO**

각각의 웹서버 성능을 비교하여 I/O model 에 대해 학습합니다