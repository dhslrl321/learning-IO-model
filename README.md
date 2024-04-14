# http server 를 만들어보며 배우는 I/O model

I/O model 은 sync/async 를 기준으로 크게 4가지로 나눌 수 있습니다.

- Synchronous model
  - blocking: **Blocking I/O**
  - non-blocking: **Non-Blocking I/O**
- Asynchronous model
  - blocking: **I/O-Multiplexing**
  - non-blocking: **AIO**

각각의 웹서버 성능을 비교하여 I/O model 에 대해 학습합니다

### Synchronous Blocking IO

- 요청당 thread 하나로 요청 처리가 완료될 때까지 block 된다
- thread 가 요청을 순차적으로(synchronous) 처리하고 다른 요청들은 block 된다

### Synchronous Non Blocking IO

- 요청당 thread 는 하나지만 요청 처리를
- thread 가 요청을 순차적으로 처리하지만 처리 결과를 기다리지 않는다 (non-block) 
- 요청 처리가 완료되었는지 확인하는 polling 사이에 다른 일을 순차적으로 수행할 수 있다

### Asynchronous Blocking IO

- IO Multiplexing 이라고 부른다
- 하나 또는 소수의 스레드가 여러 I/O 작업을 동시에 모니터링하며, 준비된 작업(읽기, 쓰기 준비 완료 등)을 식별하여 처리한다 
- 이 방법은 `select`, `poll`, `epoll` 같은 시스템 호출을 사용하여 구현된다
- event loop 에서 요청이 완료되었다는 신호를 수신한다