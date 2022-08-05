Security practices
-------------------
- Vulnerability scanning: scan libraries the service uses, also scan the containers. Use "Shift Left" (meaning early scanning) on CICD and also within container images. 
- Container security: scan the container for unapproved tools.

- there are three main techniques that you can use to secure the interservice communications: 
1) Trust the network, 
2) JSON Web Token (JWT), and 
3) Mutual Transport Layer Security (mTLS, or Mutual TLS)

Mutual TLS: 

1) https://www.javacodegeeks.com/2016/03/set-mutual-tls-authentication.html
2) https://www.cloudflare.com/learning/access-management/what-is-mutual-tls/

- rate limit to prevent DDoS: 
https://docs.microsoft.com/en-us/azure/architecture/patterns/rate-limiting-pattern
- use orchestration tools in order to use sensitive info like database password, GCP, AWS credentals
etc
- protect data in flight and in rest


references
------------
- https://geekflare.com/securing-microservices/
- https://www.aquasec.com/cloud-native-academy/cloud-native-applications/microservices-security/
- https://resilience4j.readme.io/docs/ratelimiter
