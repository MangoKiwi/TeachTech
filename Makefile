.PHONY: serve
serve: clean
	sh scripts/serve.sh

.PHONY: clean
clean:
	sh scripts/clean.sh

.PHONY: test
test: clean
	mvn test

.PHONY: run
run: clean
	mvn package	
	mvn spring-boot:run
