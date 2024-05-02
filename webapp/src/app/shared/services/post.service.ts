import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PostModel } from '../model/post.model';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class PostService {
  private posts = new Array<PostModel>();
  constructor(private http: HttpClient) {}

  savePost(post: PostModel): Observable<PostModel> {
    return this.http.post<PostModel>('api/posts', post);
  }

  getPosts(): Observable<Array<PostModel>> {
    return this.http
      .get<Array<PostModel>>('api/posts')
      .pipe(
        map((posts) => {
          this.posts = posts;
          return posts;
        })
      );
  }

  getAvailPosts():Array<PostModel> {
    return this.posts;
  }

  getPostById(id: number): Observable<PostModel> {
    return this.http.get<PostModel>(`api/posts/${id}`);
  }
}
