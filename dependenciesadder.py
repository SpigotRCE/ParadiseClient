import os
import shutil

def copy_dependencies(src_dir, dest_jar):
    # Check if the source directory exists
    if not os.path.exists(src_dir):
        print("Dependencies folder does not exist")
        return
    
    # Check if the destination directory exists, create it if it doesn't
    dest_dir = os.path.dirname(dest_jar)
    if not os.path.exists(dest_dir):
        os.makedirs(dest_dir)
    
    # Check if the destination JAR exists
    if not os.path.exists(dest_jar):
        print("Client JAR does not exist")
        return
    
    # If the destination JAR exists and it's a file, remove it
    if os.path.isfile(dest_jar):
        os.remove(dest_jar)
    
    # Iterate over files and directories in the source directory
    for item in os.listdir(src_dir):
        src_path = os.path.join(src_dir, item)
        # If it's a file, copy it to the target JAR
        if os.path.isfile(src_path):
            # Replace the existing file without warning
            shutil.copy(src_path, dest_jar)
        # If it's a directory, copy its contents recursively
        elif os.path.isdir(src_path):
            dest_subdir = dest_jar  # Update destination path for directories
            shutil.copytree(src_path, dest_subdir, dirs_exist_ok=True)

if __name__ == "__main__":
    basedir = os.getcwd()  # Get the current working directory
    src_dir = os.path.join(basedir, "libs", "dependencies")
    dest_jar = os.path.join(basedir, "target", "ParadiseClient.jar")
    
    copy_dependencies(src_dir, dest_jar)
