# BodyApp build file.

IPFS_URL:="http://localhost:8080/ipfs"
BUILD_DIR:=build


publish:
	@export hash=$(shell ipfs add -r -q $(BUILD_DIR) | tail -n 1); \
		echo $$hash >> published-versions; \
		echo $(BUILD_URL)/$$hash/app
