.PHONY: serve
serve: clean
	sh scripts/serve.sh

.PHONY: clean
clean:
	sh scripts/clean.sh

.PHONY: test
test: clean
	mvn test