try:
    from setuptools import setup
except ImportError:
    from distutils.core import setup

config = {
    'description': 'A very basic dungeon crawler...',
    'author': 'Nam Tran',
    'url': 'omn0mn0m.github.io/Dungeon-Crawler',
    'download_url': 'omn0mn0m.github.io/Dungeon-Crawler',
    'author_email': 'My email.',
    'version': '0.1',
    'install_requires': ['nose'],
    'packages': ['dungeon-crawler'],
    'scripts': [],
    'name': 'Dungeon Crawler'
}

setup(**config)
