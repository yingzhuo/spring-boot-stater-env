no_default:
	@echo "no default target"

clean:
	@mvn -f $(CURDIR)/pom.xml clean -q

compile:
	@mvn -f $(CURDIR)/pom.xml clean compile -Dmaven.test.skip=true

package:
	@mvn -f $(CURDIR)/pom.xml clean package -Dmaven.test.skip=true

deploy:
	@mvn -f $(CURDIR)/pom.xml clean deploy -Psonar -Dmaven.test.skip=true

github: clean
	@git add .
	@git commit -m "$(shell /bin/date "+%F %T")"
	@git push

.PHONY: no_default clean compile package deploy github